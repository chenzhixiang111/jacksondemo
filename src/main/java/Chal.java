import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author czx
 * @Description
 * @Version 2018-10-26 14:00
 */
@Data
public class Chal {

    @JsonProperty("Msg")
    private String msg;

    @JsonProperty("Information")
    private String information;

    @JsonProperty("GatherTime")
    private String gatherTime;

    @JsonProperty("DoorState")
    private String doorState;

    @JsonProperty("ChannelDataList")
    private List<ChannelData> channelDataList;


}
