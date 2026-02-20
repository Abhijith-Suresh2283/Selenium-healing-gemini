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

            String oldLocator){

        try{

            String prompt=

                    "You are Selenium locator healing assistant.\n\n"

                    +"Element Name: "+elementName+"\n"

                    +"Locator Type: "+locatorType+"\n"

                    +"Old Locator: "+oldLocator+"\n\n"

                    +"HTML DOM:\n"+dom+"\n\n"

                    +"IMPORTANT:\n"

                    +"Return healed locator in SAME locator type: "

                    +locatorType+"\n"

                    +"Return ONLY locator value.\n"

                    +"No explanation.";


            GenerateContentResponse response=

                    client.models.generateContent(

                            "gemini-2.0-flash-lite",

                            prompt,

                            null

                    );

            String healed=
                    response.text().trim();

            System.out.println(
                    "Healed locator: "+healed);

            return healed;

        }

        catch(Exception e){

            e.printStackTrace();

            return null;

        }

    }

}
