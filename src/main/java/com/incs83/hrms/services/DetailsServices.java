package com.incs83.hrms.services;

import com.incs83.hrms.repository.DetailsRepository;
import com.incs83.hrms.entity.Details;
import com.incs83.hrms.request.DetailsRequest;
import com.incs83.hrms.response.DetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DetailsServices {

    @Autowired
    private DetailsRepository detailsRepository;
    public List<DetailsResponse> getAll() {
        List<DetailsResponse> detailsList = new ArrayList<> ( );
        detailsRepository.findAll ( ).forEach (q -> {
            DetailsResponse detailsResponse = new DetailsResponse ( );
            detailsResponse.setAddress1 (q.getAddress1 ( ));
            detailsResponse.setAddress2 (q.getAddress2 ());
            detailsResponse.setBloodgroup (q.getBloodgroup ());
            detailsResponse.setGender (q.getGender ());
            detailsList.add (detailsResponse);
        });
        return detailsList;
    }
    public void post(DetailsRequest detailsRequest)
    {
        Details details= new Details ( );
        details.setId (UUID.randomUUID ( ).toString ( ).replaceAll ("-", ""));
        details.setAddress1 (detailsRequest.getAddress1 ( ));
        details.setAddress2 (detailsRequest.getAddress2 ( ));
        details.setBloodgroup (detailsRequest.getBloodgroup ());
        details.setGender (detailsRequest.getGender ());
        detailsRepository.save (details);
    }
    public DetailsResponse getId(String id) {

        DetailsResponse detailsResponse = new DetailsResponse ( );
        Details details = (Details) detailsRepository.findById (id).get ( );
        detailsResponse.setAddress1 (details.getAddress1 ());
        detailsResponse.setAddress2 (details.getAddress2 ());
        detailsResponse.setGender (details.getGender ());
        detailsResponse.setBloodgroup (details.getBloodgroup ());
        return detailsResponse;
    }

    public void updateDetails(String id, Details details) {

        detailsRepository.save (details);
    }

    public void deleteDetails(String id, Details details) {

        detailsRepository.deleteById (id);
    }
}
