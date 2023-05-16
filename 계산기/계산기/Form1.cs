using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace 계산기
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }


        //txtResult 초기화
        private void btnAC_Click(object sender, EventArgs e)
        {
            txtResult.Clear();
            if(txtResult.Text.Length == 0)
            {
                txtResult.Text = "0";
            }
        }

        //txtResult 에서 마지막으로 입력된 값 하나 지우기
        private void btnDel_Click(object sender, EventArgs e)
        {
            if(txtResult.Text.Length > 0)
            {
                string text = txtResult.Text;
                txtResult.Text = text.Substring(0, text.Length - 1);

                if (txtResult.Text.Length == 0)
                    txtResult.Text = "0";
            }
        }

        //사칙연산
        private void btnplus_Click(object sender, EventArgs e)
        {
            txtResult.Text += "+";
        }
        private void btnMinus_Click(object sender, EventArgs e)
        {
            txtResult.Text += "-";
        }

        private void btnMultiple_Click(object sender, EventArgs e)
        {
            txtResult.Text += "*";
        }

        private void btnDivision_Click(object sender, EventArgs e)
        {
            txtResult.Text += "/";
        }

        //숫자 입력 0 ~ 9
        private void btnNumber_Click(object sender, EventArgs e)
        {
            Button btnTmp = (Button)sender;
            if (txtResult.Text == "0")
                txtResult.Text = btnTmp.Text;
            else
                txtResult.Text += btnTmp.Text;
        }
        //소수점을 나타내기위한 함수
        private void btnPreiod_Click(object sender, EventArgs e)
        {
            if (txtResult.Text.Contains("."))
                return;
            else
                txtResult.Text += "."; 
        }

        //Root 계산 함수
        private void btnRoot_Click(object sender, EventArgs e)
        {
            try
            {
                double a = Math.Sqrt(Double.Parse(txtResult.Text));
                txtResult.Text = a.ToString();
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        //계산 함수
        private void btnEquals_Click(object sender, EventArgs e)
        {
            try
            {
                //DataTable 라이브러리 사용, Compute함수를 이용한 계산을 사용했다
                DataTable dt = new DataTable();
                var a = dt.Compute(txtResult.Text, "");
                txtResult.Text = a.ToString();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        
    }
}
