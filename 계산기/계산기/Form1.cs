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

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

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
                /*DataTable의 Compute메서드는 지정된 식을 계산한다.
                 * 두번째 인자는 필터조건이며 본 예제에서는 필요없다.
                 * 원래 DataTable에서 Compute는 필터 조건에 맞는 행을 추출하여
                 * 특정 컬럼을 집계 한다. SUM, AVG, MAX, MiN, COUNT 등*/
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
