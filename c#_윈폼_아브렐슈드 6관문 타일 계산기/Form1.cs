using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace 아브렐슈드_6관문_타일_계산기
{
    public partial class Form1 : Form
    {
        private int tileZero = 14;
        private int tileOne = 3;
        private int tileThree = 3;
        private int tileFive = 3;
        private int tileSix = 3;
        private int tileSeven = 3;
        private int tileNine = 3;
        private int tileEleven = 3;
        private int tileTwelve = 3;
        private bool isTile12Clickable = true;
        private bool isTile11Clickable = true;
        private bool isTile9Clickable = true;
        private bool isTile7Clickable = true;
        private bool isTile6Clickable = true;
        private bool isTile5Clickable = true;
        private bool isTile3Clickable = true;
        private bool isTile1Clickable = true;
        private int tile12TS = 100;
        private int tile11TS = 100;
        private int tile9TS = 100;
        private int tile7TS = 100;
        private int tile6TS = 100;
        private int tile5TS = 100;
        private int tile3TS = 100;
        private int tile1TS = 100;

        public Form1()
        {
            InitializeComponent();
        }

        private async void Tile12_Click(object sender, MouseEventArgs e)
        {
            if (isTile12Clickable && tileTwelve > 0)
            {
                if(e.Button == MouseButtons.Left) { 
                    tileTwelve--;
                    label12.Text = tileTwelve.ToString();

                    if (tileTwelve == 0)
                    {
                        isTile12Clickable = false;
                        label12.Enabled = false;

                        while (tile12TS > 0)
                        {
                            label12.Text = TimeSpan.FromSeconds(tile12TS).ToString("mm\\:ss");
                            await Task.Delay(1000);
                            tile12TS--;
                        }


                        tileTwelve = 3;
                        label12.Text = tileTwelve.ToString();
                        label12.Enabled = true;
                        isTile12Clickable = true;
                        tile12TS = 100;
                    }
                }
            }
            if(e.Button == MouseButtons.Right)
            {
                tileTwelve = 0;
                label12.Text = tileTwelve.ToString();

                if (tileTwelve == 0)
                {
                    isTile12Clickable = false;
                    label12.Enabled = false;

                    while (tile11TS > 0)
                    {
                        label12.Text = TimeSpan.FromSeconds(tile12TS).ToString("mm\\:ss");
                        await Task.Delay(1000);
                        tile12TS--;
                    }


                    tileTwelve = 3;
                    label12.Text = tileTwelve.ToString();
                    label12.Enabled = true;
                    isTile12Clickable = true;
                    tile12TS = 100;
                }
            }

        }
        private async void Tile11_Click(object sender, MouseEventArgs e) 
        {
            if (isTile11Clickable && tileEleven > 0)
            {
                if (e.Button == MouseButtons.Left)
                {
                    tileEleven--;
                    label11.Text = tileEleven.ToString();

                    if (tileEleven == 0)
                    {
                        isTile11Clickable = false;
                        label11.Enabled = false;

                        while (tile11TS > 0)
                        {
                            label11.Text = TimeSpan.FromSeconds(tile11TS).ToString("mm\\:ss");
                            await Task.Delay(1000);
                            tile11TS--;
                        }


                        tileEleven = 3;
                        label11.Text = tileTwelve.ToString();
                        label11.Enabled = true;
                        isTile11Clickable = true;
                        tile11TS = 100;
                    }
                }
            }
            if (e.Button == MouseButtons.Right)
            {
                tileTwelve = 0;
                label11.Text = tileTwelve.ToString();

                if (tileTwelve == 0)
                {
                    isTile11Clickable = false;
                    label11.Enabled = false;

                    while (tile11TS > 0)
                    {
                        label11.Text = TimeSpan.FromSeconds(tile11TS).ToString("mm\\:ss");
                        await Task.Delay(1000);
                        tile11TS--;
                    }


                    tileTwelve = 3;
                    label11.Text = tileTwelve.ToString();
                    label11.Enabled = true;
                    isTile12Clickable = true;
                    tile11TS = 100;
                }
            }
        }
        private async void Tile6_Click(object sender, MouseEventArgs e) { }
        private async void Tile5_Click(object sender, MouseEventArgs e) { }
        private async void Tile3_Click(object sender, MouseEventArgs e) { }
        private async void Tile1_Click(object sender, MouseEventArgs e) { }
    }
}
