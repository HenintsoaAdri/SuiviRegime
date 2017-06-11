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
    public partial class EditionClient : Form
    {
        private ServiceReference1.BanqueWSClient proxy;
        public EditionClient()
        {
            InitializeComponent();
            proxy = new ServiceReference1.BanqueWSClient();
        }

        private void buttonModifier_Click(object sender, EventArgs e)
        {
            ServiceReference1.client clt = new ServiceReference1.client();
            clt.nom = textBoxNom.Text;
            clt.prenom = textBoxPrenom.Text;
            clt.adresse = textBoxadrese.Text;
            clt.mail = textBoxMail.Text;
            proxy.updateClient(clt);
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {
            ServiceReference1.client clt = new ServiceReference1.client();
            clt.nom = textBoxNom.Text;
            clt.prenom = textBoxPrenom.Text;
            clt.adresse = textBoxadrese.Text;
            clt.mail = textBoxMail.Text;
            proxy.DeleteClient(clt);
        }
    }
}
