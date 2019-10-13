using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Telesign;
using Login.Data;
using Login.Models.Entity;

namespace Login.Business
{
    public class CampaignBusiness
    {
        public bool AddCampaign(CampaignEntity entityCampaign)
        {
            Campaign dataCampaigns = new Campaign();
            dataCampaigns.AddCampaign(entityCampaign);
            return false;
        }

        public void SendCampaign(string num)
        {
            string customerId = "6CA02481-78A2-4F0D-8E62-2D6E00C6ED96";
            string apiKey = "xRI9I+G576LNFkt9V1q065x9PhzDHKGYHYCEWjvaBXNG0ogkok3K9dcFdcaMCj3AQL8SKA4WU9DrFbVkT5pywQ==";

            string phoneNumber = "+57"+ num;

            string message = "Telesign: Campaña de salud";
            string messageType = "ARN";

            try
            {
                MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
                RestClient.TelesignResponse telesignResponse = messagingClient.Message(phoneNumber, message, messageType);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }            
        }

        public string CheckPhone(string num)
        {
            string valida = "";
            string customerId = "6CA02481-78A2-4F0D-8E62-2D6E00C6ED96";
            string apiKey = "xRI9I+G576LNFkt9V1q065x9PhzDHKGYHYCEWjvaBXNG0ogkok3K9dcFdcaMCj3AQL8SKA4WU9DrFbVkT5pywQ==";

            string phoneNumber = "+57"+num;

            string accountLifecycleEvent = "create";

            try
            {
                ScoreClient scoreClient = new ScoreClient(customerId, apiKey);
                RestClient.TelesignResponse telesignResponse = scoreClient.Score(phoneNumber, accountLifecycleEvent);

                if (telesignResponse.OK)
                {
                    valida = string.Format("The phone number {0} has a risk level '{1}' and the recommendation is '{2}' the evaluation.",
                        phoneNumber,
                        telesignResponse.Json["risk"]["level"],
                        telesignResponse.Json["risk"]["recommendation"]);
                    
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                valida = e.Message;
            }
            return valida;


        }

        public void SendVoiceCallWithVerificationCode(string num)
        {
            string customerId = "6CA02481-78A2-4F0D-8E62-2D6E00C6ED96";
            string apiKey = "xRI9I+G576LNFkt9V1q065x9PhzDHKGYHYCEWjvaBXNG0ogkok3K9dcFdcaMCj3AQL8SKA4WU9DrFbVkT5pywQ==";

            string phoneNumber = "+57"+ num;

            string message = "You're scheduled for a dentist appointment at 2:30PM.";
            string messageType = "ARN";

            try
            {
                MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
                RestClient.TelesignResponse telesignResponse = messagingClient.Message(phoneNumber, message, messageType);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }

            Console.WriteLine("Press any key to quit.");
            Console.ReadKey();
        }

        public void SendVoiceCall(string num)
        {
            string customerId = "6CA02481-78A2-4F0D-8E62-2D6E00C6ED96";
            string apiKey = "xRI9I+G576LNFkt9V1q065x9PhzDHKGYHYCEWjvaBXNG0ogkok3K9dcFdcaMCj3AQL8SKA4WU9DrFbVkT5pywQ==";

            string phoneNumber = "+57"+num;

            string message = "You're scheduled for a dentist appointment at 2:30PM.";
            string messageType = "ARN";

            try
            {
                VoiceClient voiceClient = new VoiceClient(customerId, apiKey);
                RestClient.TelesignResponse telesignResponse = voiceClient.Call(phoneNumber, message, messageType);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }

            Console.WriteLine("Press any key to quit.");
            Console.ReadKey();
        }
    }
}
    