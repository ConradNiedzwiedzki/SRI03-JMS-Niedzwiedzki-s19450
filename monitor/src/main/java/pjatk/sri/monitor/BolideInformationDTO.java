package pjatk.sri.monitor;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
class BolideInformationDTO implements Serializable
{
	private LocalDateTime currentTime;
    private Long temperatureOfEngine;
    private Long temperatureOfOil;

    public static BolideInformationDTO creafeFromMap(Map<String, Object> map)
	{
        return BolideInformationDTO
				.builder()
                .currentTime(LocalDateTime.parse((String)map.get("currentTime"))
                .temperatureOfEngine((Long)map.get("temperatureOfEngine"))
                .temperatureOfOil((Long)map.get("temperatureOfOil")))
                .build();
    }

    public Map<String, Object> convertToHashMap()
	{
        HashMap<String, Object> hashMap = new HashMap<>();
		
		hashMap.put("currentTime", currentTime.toString());
        hashMap.put("temperatureOfEngine", temperatureOfEngine);
        hashMap.put("temperatureOfOil", temperatureOfOil);        
		
        return hashMap;
    }
}
