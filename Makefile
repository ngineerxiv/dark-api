credentials=../credentials

deploy: twitter4j.properties
	$(MAKE) appengine-deploy

appengine-deploy:
	./tool/bin/sbt appengineDeploy

twitter4j.properties:
	cp -f $(credentials)/$@ ./src/main/resources/$@

