using System;
using System.Collections.Generic;
using System.Drawing.Drawing2D;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace 아브렐슈드_6관문_타일_계산기
{
    public class DiamondButton : Button
    {
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
}
