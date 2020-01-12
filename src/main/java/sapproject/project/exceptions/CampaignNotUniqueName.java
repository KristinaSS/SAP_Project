package sapproject.project.exceptions;

public class CampaignNotUniqueName extends RuntimeException {
    private static final long serialVersionUID = -3496053549495473363L;

    public CampaignNotUniqueName(String campaignName) {
        super("Name not unique of campaign: " + campaignName);
    }
}
