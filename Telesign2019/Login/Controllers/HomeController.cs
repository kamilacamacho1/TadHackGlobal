using Login.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Login.Business;
using Login.Models.Entity;
using System.Net;

namespace Login.Controllers
{
    public class HomeController : Controller
    {

        CampaignBusiness campB = new CampaignBusiness();
        private BDHack2019Entities db = new BDHack2019Entities();

        public ActionResult Index()
        {
            List<CampaignEntity> listCampaign = new List<CampaignEntity>();

            using (BDHack2019Entities db = new BDHack2019Entities())
            {
                listCampaign = (from p in db.TblCampaign
                                select new CampaignEntity
                                {
                                    IdCampaign = p.IdCampaign,
                                    Name = p.Cam_Name,
                                    Location = p.Cam_Location,
                                    language = p.Cam_language,
                                    ContactsList = p.cam_ContactsList
                                }
                                ).ToList();
            }

            return View(listCampaign);
        }

        public ActionResult FormCampaign()
        {
            return View();
        }

        [HttpPost]
        public ActionResult AddCampaign()
        {
            CampaignBusiness campaignB = new CampaignBusiness();
            campaignB.AddCampaign(new Models.Entity.CampaignEntity { });

            return View("Index");
        }

        // GET: Provedores/Edit/5
        public ActionResult SendCampaign(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TblCampaign tb_Campaign = db.TblCampaign.Find(id);
            if (tb_Campaign == null)
            {
                return HttpNotFound();
            }
            else
            {
                CampaignBusiness campB = new CampaignBusiness();

                var num = tb_Campaign.cam_ContactsList.Trim();

                campB.CheckPhone(num);

                campB.SendCampaign(num);

            }
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult SendCampaign([Bind(Include = "IdProvedor,Nombre")] TblCampaign tb_campa)
        {
            if (ModelState.IsValid)
            {

                return RedirectToAction("Index");
            }
            return View(tb_campa);
        }

        public ActionResult About()
        {
            ViewBag.Message = "3222571199";

            var checkNumber = campB.CheckPhone("3222571199");

            ViewBag.number = checkNumber;

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "3222571199";

            //campB.SendVoiceCall("3222571199");

            return View();
        }

        public ActionResult World_Population()
        {
            return View();
        }
    }
}