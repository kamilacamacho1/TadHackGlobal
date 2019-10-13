using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Login.Models.Entity
{
    public class UserEntity
    {
        public int idUser { get; set; }

        public string First_Name { get; set; }

        public string Last_Name { get; set; }

        public string Password { get; set; }

        public string Nationality { get; set; }

        public string BirthDate { get; set; }

        public string Addictions { get; set; }

        public string  Diseases { get; set; }

        public int Son { get; set; }

        public string  EducationLevel { get; set; }

        public string  Gender { get; set; }

        public string  Icome { get; set; }

        public string User_Religion { get; set; }

        public string Location { get; set; }

    }
}