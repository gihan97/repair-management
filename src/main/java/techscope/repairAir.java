package techscope;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/repairAir")
public class repairAir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cID=request.getParameter("cID");
		String com=request.getParameter("com");
		String date=request.getParameter("date");
		String des=request.getParameter("des");
		String spare=request.getParameter("spare");
		String qty=request.getParameter("qty");
		String cost=request.getParameter("cost");
		
		boolean istrue;
		
		istrue=repairDButil.insertAirRepair(cID, com, date, des, spare, qty, cost);
		
		if(istrue==true)
		{
			try {
				List<onGoingAir> onGoingAir = repairDButil.repairAirID();
				request.setAttribute("onGoingAir", onGoingAir);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dis=request.getRequestDispatcher("repairID.jsp");
			dis.forward(request, response);
		}
		else
		{
			RequestDispatcher dis=request.getRequestDispatcher("repairFormAir.jsp");
			dis.forward(request, response);
		}
	}
}

