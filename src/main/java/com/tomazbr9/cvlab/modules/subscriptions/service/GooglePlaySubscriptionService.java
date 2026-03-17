package com.tomazbr9.cvlab.modules.subscriptions.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.SubscriptionPurchase;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Collections;

@Service
public class GooglePlaySubscriptionService {

    @Value("${google.play.package.name}")
    private String packageName;

    // Lemos o caminho do arquivo JSON que configuramos no application.properties
    @Value("${google.play.credentials.path}")
    private String credentialsPath;

    private AndroidPublisher androidPublisher;

    // Este método roda automaticamente assim que o Spring Boot liga
    @PostConstruct
    public void init() {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

            // Abre o arquivo JSON da Conta de Serviço
            InputStream in = getClass().getResourceAsStream(credentialsPath.replace("classpath:", "/"));

            if (in == null) {
                throw new RuntimeException("Arquivo de credenciais do Google Play não encontrado!");
            }

            // Autentica usando as permissões de leitura de faturamento do Google Play
            GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                    .createScoped(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER));

            // Constrói o cliente oficial da API do Google
            androidPublisher = new AndroidPublisher.Builder(httpTransport, jsonFactory, new HttpCredentialsAdapter(credentials))
                    .setApplicationName("CVLabMobile-Backend") // Pode colocar o nome do seu app aqui
                    .build();

            System.out.println("Conexão com Google Play API estabelecida com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao inicializar a API do Google Play: " + e.getMessage());
        }
    }

    /**
     * Valida um recibo de assinatura diretamente nos servidores do Google.
     * * @param subscriptionId O ID do plano criado na Play Store (ex: "cv_premium_mensal")
     * @param purchaseToken O recibo gigante gerado pelo celular do usuário após a compra
     * @return O objeto oficial do Google com os dados da compra, ou null se for inválido
     */
    public SubscriptionPurchase verifySubscription(String subscriptionId, String purchaseToken) {
        try {
            // Faz a requisição (GET) para a API do Google
            return androidPublisher.purchases().subscriptions()
                    .get(packageName, subscriptionId, purchaseToken)
                    .execute();

        } catch (Exception e) {
            // Se cair aqui, o token é falso, inválido ou houve erro de rede
            System.err.println("Erro ao validar token no Google: " + e.getMessage());
            return null;
        }
    }
}