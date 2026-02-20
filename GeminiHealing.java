package com.healthproof.qa;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GeminiHealing {

    static Client client =
            new Client.Builder()
                    .apiKey("YOUR_GEMINI_API_KEY")
                    .build();

    public static String heal(

            String dom,

            String elementName,

            String locatorType,

            String oldLocator) {

        try {

            String prompt =

                    "You are a Selenium locator healing assistant.\n\n"

                            + "Element Name: "
                            + elementName + "\n"

                            + "Old Locator Type: "
                            + locatorType + "\n"

                            + "Old Locator Value: "
                            + oldLocator + "\n\n"

                            + "HTML DOM:\n"
                            + dom +

                            "\n\nFind the best updated XPath for this element."

                            + "\nReturn ONLY XPath.";

            GenerateContentResponse response =

                    client.models.generateContent(

                            "gemini-2.0-flash-lite",

                            prompt,

                            null

                    );

            String xpath =
                    response.text();

            System.out.println(
                    "Healed XPath from Gemini: "
                            + xpath);

            return xpath.trim();

        }

        catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}
