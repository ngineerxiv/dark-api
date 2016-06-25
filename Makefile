credentials=../credentials

appengine-deploy:
	sbt appengineDeploy

twitter4j.properties:
	cp -f $(credentials)/$@ ./src/main/resources/$@
