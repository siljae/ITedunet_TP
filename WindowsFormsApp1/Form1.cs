using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            for (int i = 0; i < 8; i++)
            {
                DiamondButton diamondButton = new DiamondButton();
                diamondButton.Text = "3";
                diamondButton.Size = new Size(100, 100);
                diamondButton.Location = new Point(50 + i * 120, 50); // 버튼 위치 조정

                Controls.Add(diamondButton);
            }
        }

    }
}
