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
    public partial class ListeClient : Form
    {
        private ServiceReference1.BanqueWSClient proxy;
        public ListeClient()
        {
            InitializeComponent();
            proxy = new ServiceReference1.BanqueWSClient();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            ServiceReference1.client[] listeClients = proxy.getAllClient();
            DataTable dataTable = new DataTable("TableClients");
            dataTable.Columns.Add("Nom");
            dataTable.Columns.Add("Prenom");
            dataTable.Columns.Add("Mail");
            dataTable.Columns.Add("Adresse");
            dataTable.Columns.Add("Naissance");
            dataTable.Columns.Add("Sexe");
            for(int i = 0; i < listeClients.Length; i++)
            {
                dataTable.Rows.Add(listeClients[i].nom, listeClients[i].prenom, listeClients[i].mail, listeClients[i].adresse, listeClients[i].dateNaissance, listeClients[i].sexe);

            }
            dataGridView1.DataSource = dataTable;

        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }
}
