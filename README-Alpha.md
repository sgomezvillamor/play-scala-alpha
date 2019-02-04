# Intro

Everything can fe found here: https://github.com/sgomezvillamor/play-scala-alpha
Note I'm working on brach 2.5.x: https://github.com/sgomezvillamor/play-scala-alpha/commits/2.5.x


# Design consideration

- Use play framework to build the service
- Analysis of `BasicJobFair`:
  - Implementation is very imperative (loops, breaks, vars), not functional... don't like that :-)
  - pros: quite simple implementation which already works with the provided example
  - cons: order of engineers impacts on the resulting matches
  - mitigation: since the order is important, can we rank engineers? according to qualifications, 
  years in the company, ...?

# Activity log

- Thursday, about 1 hour to:
  - clone https://github.com/playframework/play-scala-starter-example (much easier than creating project from scratch)
  - set up POST /jobfair service
  - manage JSON request/response
  
- Friday, about 15mins to:
  - remove some unnecessary code from play-scala-starter
  
- Saturday, about 45mins to:
  - clean dependencies in build.sbt and set up unit tests
  - first basic implementation for JobFair service 
  
- Monday, about 30mins to:
  - Analysis of `BasicJobFair` (see above in design considerations) and add more tests