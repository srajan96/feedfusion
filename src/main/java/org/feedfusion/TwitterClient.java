import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.StatusUpdate;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

 
    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("Olwk4ncLNgYZcROLvP9oAFrgv")
          .setOAuthConsumerSecret("eht2OHYflAV1Cu8GP9XA46zm7KbiivY35TytvJ91aMX67brKEF")
          .setOAuthAccessToken("1668200646-cR34ReTvqAUgQGNvFQWlbGNkGgksXvq7yFHWlO6")
          .setOAuthAccessTokenSecret("VAU21C4JHFCrbBfwDfTkdLEkSkpjBD7VFkFowFwLPAuoC")
          .setHttpProxyHost("10.25.0.42") 
          .setHttpProxyPort(3128) 
          .setHttpProxyUser("kumark-d-14") 
          .setHttpProxyPassword("abc123") ;
	
	
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
              List<Status> statuses = twitter.getHomeTimeline();
    System.out.println("Showing home timeline.");
    for (Status status : statuses) 
        System.out.println(status.getUser().getName() + ":" +     status.getText());
     twitter.updateStatus("Jai dewas!");
        } catch (Exception e) {
            System.out.println(e);
        }
      
   
        /*String consumerKey = "Olwk4ncLNgYZcROLvP9oAFrgv";

        //Your Twitter App's Consumer Secret
        String consumerSecret = "Olwk4ncLNgYZcROLvP9oAFrgv";

        //Your Twitter Access Token
        String accessToken = "1668200646-cR34ReTvqAUgQGNvFQWlbGNkGgksXvq7yFHWlO6";

        //Your Twitter Access Token Secret
        String accessTokenSecret = "VAU21C4JHFCrbBfwDfTkdLEkSkpjBD7VFkFowFwLPAuoC";

        //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();

        //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();

        //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);

        //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        try {
           ResponseList<Status> a=twitter.getUserTimeline(new Paging(1,5));
           for(Status b : a)
               System.out.println(b.getText());
        } catch (Exception e) {
        }
*/    
}
}
