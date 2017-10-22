package servlets;

import model.Donation;

import javax.ejb.DependsOn;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/voucher")
public class CompleteVoucherServlet extends AbstractServlet {
    @Override
    protected ResponseEntity<?> processGet(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<? extends Donation> responseEntity = new ResponseEntity<>();
        String voucherCode = request.getParameter("voucher_code");
        return responseEntity;
    }
}
