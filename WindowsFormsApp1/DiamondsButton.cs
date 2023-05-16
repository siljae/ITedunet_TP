using System;
using System.Collections.Generic;
using System.Drawing.Drawing2D;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;
using static System.Windows.Forms.LinkLabel;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public class DiamondButton : Button
    {
        private int value = 3; // 초기 값

        public DiamondButton()
        {
            Click += DiamondButton_Click;
        }

        private void DiamondButton_Click(object sender, EventArgs e)
        {
            if (value > 0)
            {
                value--;
                Text = value.ToString();

                if (value == 0)
                {
                    Timer timer = new Timer();
                    timer.Interval = 1000; // 1초
                    timer.Tick += Timer_Tick;
                    timer.Start();
                }
            }
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            Timer timer = (Timer)sender;
            timer.Stop();
            timer.Dispose();

            MessageBox.Show("타이머 작동!");
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            GraphicsPath path = new GraphicsPath();
            path.AddPolygon(new Point[] {
            new Point(Width / 2, 0),
            new Point(Width, Height / 2),
            new Point(Width / 2, Height),
            new Point(0, Height / 2)
        });

            this.Region = new Region(path);

            base.OnPaint(e);
        }
    }

    public class MainForm : Form
    {
        public MainForm()
        {
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
