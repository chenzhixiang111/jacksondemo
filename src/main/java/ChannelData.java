import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author czx
 * @Description
 * @Version 2018-10-26 14:16
 */
@Data
public class ChannelData {

    @JsonProperty("ChannelID")
    private String channelID;

    @JsonProperty("ChannelValue")
    private String channelValue;

    @JsonProperty("GatherTime")
    private String gatherTime;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Msg")
    private String msg;

    @JsonProperty("CollectID")
    private String collectID;

    @JsonProperty("ControlParam")
    private String controlParam;

    @JsonProperty("ControlCount")
    private String controlCount;

    @JsonProperty("Remark")
    private String remark;

    @JsonProperty("DataSource")
    private String dataSource;

    @JsonProperty("StorageTime")
    private String storageTime;

    @JsonProperty("ChannelType")
    private String channelType;

    @JsonProperty("DisplayValue")
    private String displayValue;

}
