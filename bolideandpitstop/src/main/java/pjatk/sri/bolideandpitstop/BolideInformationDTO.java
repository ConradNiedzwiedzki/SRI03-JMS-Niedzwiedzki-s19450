package pjatk.sri.bolideandpitstop;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class BolideInformationDTO implements Serializable
{
	private LocalDateTime currentTime;
	private Long temperatureOfOil;
    private Long temperatureOfEngine;

    BolideInformationDTO(LocalDateTime currentTime, Long temperatureOfEngine, Long temperatureOfOil)
	{
		this.currentTime = currentTime;
        this.temperatureOfEngine = temperatureOfEngine;
        this.temperatureOfOil = temperatureOfOil;
    }

    @Override
    public String toString()
	{
        return "-- Time: " + currentTime.toString() + " -- Engine temperature is " + temperatureOfEngine + "C, oil temperature is " + temperatureOfOil "C.";
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
