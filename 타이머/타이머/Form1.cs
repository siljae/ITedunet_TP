using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace 타이머
{
    public partial class Form1 : Form
    {
        private int setTimer;
        private int focusTimer;
        private int remainTimer;
        public Form1()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, EventArgs e)
        {
            setTimer = Convert.ToInt32(setTime.Text);
            focusTimer = Convert.ToInt32(focusTime.Text);
            timer1.Interval = 1000;
            timer1.Start();
            
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            setTimer--;
            label3.Text = setTimer.ToString();
            if(setTimer <= focusTimer)
            {
                label3.BackColor = Color.Red;
            }

            if (setTimer == 0)
            {
                label3.Text = setTimer.ToString();
                timer1.Stop();
                MessageBox.Show("종료");
            }
        }

        private void label3_Click(object sender, EventArgs e)
        {
            
        }
    }
}
