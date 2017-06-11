using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsSoap
{
    public partial class AjoutClient : Form
    {
        private ServiceReference1.BanqueWSClient proxy;
        public AjoutClient()
        {
            InitializeComponent();
            proxy = new ServiceReference1.BanqueWSClient();
        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            ServiceReference1.client clt = new ServiceReference1.client();
            clt.nom = textBoxNom.Text;
            clt.prenom = textBoxPrenom.Text;
            clt.adresse = textBoxAdresse.Text;
            clt.sexe = textBoxSexe.Text;
            clt.mail = textBoxMail.Text;
            proxy.AjoutClient(clt);

        }
    }
}
