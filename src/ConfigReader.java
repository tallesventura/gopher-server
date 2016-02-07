
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tallesventura
 */
public class ConfigReader {
    
    private final String cfgPath = "config/cfg.properties";
    
    public Map<String,String> readConfig() throws IOException {

        Map<String,String> propMap = new HashMap();
        Properties prop = new Properties();

        prop.load(new FileInputStream(cfgPath));       
        Set<Entry<Object,Object>> s = prop.entrySet();
        
        for(Entry e : s){
            propMap.put(e.getKey().toString(), e.getValue().toString());
        }
        
        return propMap;
    }
}
