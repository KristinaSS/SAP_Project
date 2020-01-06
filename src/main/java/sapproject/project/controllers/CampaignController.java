package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Campaign;
import sapproject.project.payload.CampaignPayload;
import sapproject.project.payload.ProductCampaignPayload;
import sapproject.project.payload.UpdateAccount;
import sapproject.project.repository.CampaignRepository;
import sapproject.project.services.classes.CampaignService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("campaign")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Campaign> getAllCampaigns(){
        return campaignService.findAll();
    }

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public Campaign getCampaign(@Valid @RequestBody String campaignId){
        return campaignService.getCampaignById(Integer.parseInt(campaignId));
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCampaign(@Valid @RequestBody String campaignId){
        campaignService.deleteCampaignById(Integer.parseInt(campaignId));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Campaign createCampaign(@Valid @RequestBody CampaignPayload campaign){
        return campaignService.createCampaign(campaign);
    }

    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public Campaign editCampaign(@Valid @RequestBody CampaignPayload campaign){
        return campaignService.editCampaign(campaign);
    }

    @PostMapping("/addToCampaign")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCampaign(@Valid @RequestBody ProductCampaignPayload campaign){
        campaignService.addToCampaign(campaign);
    }

}
