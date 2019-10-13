using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Login.Models.Entity
{
    public class CampaignEntity
    {
        public int IdCampaign { get; set; }
        public string Name { get; set; }
        public string language { get; set; }
        public string Location { get; set; }
        public string ContactsList { get; set; }
    }
}