import spray.revolver.RevolverPlugin.Revolver
import twirl.sbt.TwirlPlugin.Twirl


organization  := "com.hiynn"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

val droolsVersion = "6.0.1.Final"

libraryDependencies ++= {
  val akkaV = "2.1.4"  
  val sprayV = "1.1.1"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-http"     % sprayV,
    "io.spray"            %   "spray-util"     % sprayV,
    "io.spray"            %   "spray-httpx"     % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV  % "test",
    "io.spray"            %  "spray-json_2.10"    % "1.2.5",
    "org.json4s"          %% "json4s-native"      % "3.2.4",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test",
     "junit" 				% "junit"			 % "4.8.1" % "test"   
  )
}

  resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)
  
// Drools
resolvers ++= Seq( 
	"Scala sbt repository" at "http://repo.scala-sbt.org/scalasbt/repo/",
    "boss 3rdparty" at "https://repository.jboss.org/nexus/content/repositories/releases/",
    "JBoss public" at "http://repository.jboss.org/nexus/content/groups/public/",
    "sonatype-public" at "https://oss.sonatype.org/content/groups/public",
    "spray repo" at "http://repo.spray.io"
	)

// Drools
libraryDependencies ++= {
"org.kie" % "kie-api" % droolsVersion ::
List("drools-compiler", "drools-core", "drools-jsr94", "drools-decisiontables", "knowledge-api")
.map("org.drools" % _ % droolsVersion)
}


// Hbase 0.96.1.1-cdh5.0.2
libraryDependencies ++= {
  val HbaseVersion = "0.98.1-hadoop2"
  Seq(
  	"org.apache.hbase" % "hbase-client" % HbaseVersion, 
	"org.apache.hbase" % "hbase-server" % HbaseVersion, 
	"org.apache.hbase" % "hbase-shell" % HbaseVersion, 
	"org.apache.hbase" % "hbase-examples" % HbaseVersion,
	"org.apache.hbase" % "hbase-thrift" % HbaseVersion, 
	"org.apache.hbase" % "hbase-testing-util" % HbaseVersion,
	"org.apache.hadoop" % "hadoop-hdfs" % "2.2.0"
  )
}

//neo4j 
libraryDependencies ++= {
  val neo4jVersion = "1.8.2"
  Seq(
  	 "org.neo4j" % "neo4j-kernel" % neo4jVersion classifier "tests", 
     "org.neo4j" % "neo4j" % neo4jVersion,
	 "org.neo4j" % "neo4j-udc" % neo4jVersion,
	 "org.neo4j" % "neo4j-shell" % neo4jVersion,
	 "org.neo4j" % "neo4j-management" % neo4jVersion,
	 "org.neo4j" % "neo4j-lucene-index" % neo4jVersion,
	 "org.neo4j" % "neo4j-jmx" % neo4jVersion,
	 "org.neo4j" % "neo4j-ha" % neo4jVersion,
	 "org.neo4j" % "neo4j-graphviz" % neo4jVersion,
	 "org.neo4j" % "neo4j-graph-matching" % neo4jVersion,
	 "org.neo4j" % "neo4j-graph-algo" % neo4jVersion,
	 "org.neo4j" % "neo4j-enterprise" % neo4jVersion,
	 "org.neo4j" % "neo4j-cypher" % neo4jVersion,
	 "org.neo4j" % "neo4j-consistency-check" % neo4jVersion,
	 "org.neo4j" % "neo4j-com" % neo4jVersion,
	 "org.neo4j" % "neo4j-backup" % neo4jVersion,
	 "org.neo4j" % "neo4j-advanced" % neo4jVersion,
	 "org.neo4j" % "server-api" % neo4jVersion,
	 "org.neo4j.app" % "neo4j-server" % neo4jVersion,
	 "org.neo4j" % "neo4j-kernel" % neo4jVersion,
	 "com.sun.jersey" % "jersey-bundle" % "1.17"
  )
}


Seq(Revolver.settings: _*)
Seq(Twirl.settings: _*)
