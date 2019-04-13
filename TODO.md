# getAll with a test.
it works from a browser.  WebClient fails
    * add the mongo repository.  
    * figure out how to do a test without having a mongo repo, ie have
    the handler return a hardcoded List of stuff
    
    
1. figure out how to specify the database to use.  create it if it does not exist
1. user/password for mongo
1. integration testing
    1. Got the basic tests running. 
        1. not sure what db/collection is getting used.  I do get stuff creatd with ids
        1. figure out how to test with the flux/monos object
        1. figure out how to get mongo 4.x
        `		EnumSet<Feature> features = Version.Main.PRODUCTION.getFeatures();
         		IFeatureAwareVersion iFeatureAwareVersion = Versions.withFeatures(new GenericVersion("4.0.2"), features);
`