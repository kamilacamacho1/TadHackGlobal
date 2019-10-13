using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using Login.Models;
using Login.Models.Entity;

namespace Login.Data
{
    public class Campaign
    {
        BDHack2019Entities entidadData = new BDHack2019Entities();

        public bool AddCampaign(CampaignEntity entityCampaign)
        {
            bool insertCampaign = false;

            try
            {
                using (SqlConnection con = new SqlConnection(entidadData.Database.Connection.ConnectionString))
                {
                    using (SqlCommand cmd = new SqlCommand("SP_CreateCampaign",con))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;

                        //Parametros
                        cmd.Parameters.Add("@pCam_Name", SqlDbType.VarChar);
                        cmd.Parameters.Add("@pCam_language", SqlDbType.VarChar);
                        cmd.Parameters.Add("@PCam_Location", SqlDbType.VarChar);
                        cmd.Parameters.Add("@Pcam_ContactsList", SqlDbType.VarChar);
                        //Asignar valores
                        cmd.Parameters["@pCam_Name"].Value = entityCampaign.Name;
                        cmd.Parameters["@pCam_language"].Value = entityCampaign.language;
                        cmd.Parameters["@PCam_Location"].Value = entityCampaign.Location;
                        cmd.Parameters["@Pcam_ContactsList"].Value = entityCampaign.ContactsList;
                        //Connection open
                        con.Open();
                        int numero = cmd.ExecuteNonQuery();

                        con.Close();
                        insertCampaign = true;
                    }
                }

            }
            catch (Exception e )
            {

                Console.WriteLine(e);
                insertCampaign = false;
            }

            return insertCampaign;
        }
    }
}