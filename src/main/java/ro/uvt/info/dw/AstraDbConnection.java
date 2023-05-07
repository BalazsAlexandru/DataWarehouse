package ro.uvt.info.dw;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Paths;

public class AstraDbConnection {

    @Value("${spring.cassandra.username}")
    private static String CLIENT_ID;

    @Value("${spring.cassandra.password}")
    private static String CLIENT_SECRET;

    public static void main(String[] args) {
        // Create the CqlSession object:
        try (CqlSession session = CqlSession.builder()
                // CloudSecureConnectBundle filename (secure-connect-dw-class-2023.zip) needs to be adjusted to match your database name
                .withCloudSecureConnectBundle(Paths.get("src\\main\\resources\\secure-connect-datawarhouse-2023.zip"))
                .withAuthCredentials(CLIENT_ID, CLIENT_SECRET)
                .build()) {
            // Select the release_version from the system.local table:
            var rs = session.execute("select release_version from system.local");
            var row = rs.one();
            //Print the results of the CQL query to the console:
            if (row != null) {
                System.out.println(row.getString("release_version"));
            } else {
                System.out.println("An error occurred.");
            }
        }
        System.exit(0);
    }
}