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
- State of the art: matching markets
  - Matching markets is a research topic in economy, different from commodity markets, where
  prices manage the market
  - According to challenge requirements, this is a many-to-one matching market problem
    - One-to-one: marriage
    - Many-to-one: employment (eg. firms and consultants: a firm employees many consultants but a 
     consultant just works for one firm)
    - Many-to-many: employment when the employee can work for multiple firms at the same time
  - Although there is a significant amount of work done, this seems still an active research topic.
  - I haven't found a public implementation for the many-to-one problem yet
  - Deeper research is required

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
  
- Monday night, about 1h to:
  - Review state of the art on matching markets (see above in design considerations)