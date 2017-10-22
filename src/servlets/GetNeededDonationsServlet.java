package servlets;

import model.Donation;
import model.MonetaryDonation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@WebServlet("/listneeded")
public class GetNeededDonationsServlet extends AbstractServlet {

    @Override
    protected ResponseEntity<?> processGet(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<Set<? extends Donation>> responseEntity = new ResponseEntity<>();
        responseEntity.setData(db.getNeededMonetaryDonations());
        return responseEntity;
    }
}
