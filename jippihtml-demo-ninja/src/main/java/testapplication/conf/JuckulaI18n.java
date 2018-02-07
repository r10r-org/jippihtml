//package testapplication.conf;
//
//import com.google.common.base.Optional;
//import ninja.Context;
//import ninja.Result;
//import ninja.i18n.Messages;
//
//
///**
// * Just a thing wrapper to use Ninja's I18n facility inside Juckula templates.
// * 
// * FIXME: Should be converted to a Guice provider for easy use...
// * 
// * @author ra
// */
//public class JuckulaI18n {
//    
//    private final Context context;
//    private final Optional<Result> result = Optional.absent();
//    private final Messages messages;
//    
//    public JuckulaI18n(Messages messages, Context context) {
//        this.context = context;
//        this.messages = messages;
//    }
//
//    public Optional<String> get(String key, Object... parameter) {
//        
//        return messages.get(key, context, result, parameter);
//    
//    }
//    
//    public String getWithDefault(String key, String defaultMessage) {
//          
//      return messages.getWithDefault(key, defaultMessage, context, result);
//      
//    }
//    
//}
