package servlets;

import model.Donation;
import model.MonetaryDonation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/listneeded")
public class GetNeededDonationsServlet extends AbstractServlet {

    @Override
    protected ResponseEntity<?> processGet(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<Set<? extends Donation>> responseEntity = new ResponseEntity<>();

        Set<Donation> allNeededDonations = new HashSet<>();
        allNeededDonations.addAll(db.getNeededMonetaryDonations());
        allNeededDonations.addAll(db.getNeededPhysicalDonations());
        System.out.println(db.getNeededPhysicalDonations().size()+"size");
        responseEntity.setData(allNeededDonations);
        return responseEntity;
    }
}


