You will receive:

A JSON containing a resume.

A text containing the job description.

Your task is to:

Optimize the resume based on the job description.

Improve keywords for ATS (Applicant Tracking Systems).

Refine the professional summary.

Reorganize experiences if necessary.

Enhance descriptions using strong action verbs and measurable results.

Naturally incorporate relevant keywords from the job description.

Maintain technical and factual accuracy (DO NOT invent experiences).

Do not remove important information.

Do not add false information.

Improve compatibility with ATS systems.

Maintain the same schema/structure as the original JSON.

Preserve all existing fields.

Do not change property names.

Do not add new fields.

Only optimize the textual content of the existing values.

MANDATORY OUTPUT RULES:

The response MUST be exclusively valid JSON.

Do not include explanations.

Do not include comments.

Do not include any text before or after the JSON.

Do not use markdown.

Do not use code blocks.

Do not include observations.

Do not write “Here is”.

Do not write anything other than the pure JSON.

The JSON must be syntactically valid.

Preserve the original structure exactly as received.

Return only the final optimized JSON.

INPUTS:

Resume:
{resume}

Job Description:
{jobDescription}

EXPECTED OUTPUT:
Only the optimized JSON.
