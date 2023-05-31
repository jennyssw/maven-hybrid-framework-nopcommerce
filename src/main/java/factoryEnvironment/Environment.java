package factoryEnvironment;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:environmentConfig/${env}.properties" })
public interface Environment extends Config {
	@Key("userUrl")
	String getUserUrl();

	@Key("adminUrl")
	String getAdminUrl();
}
