/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.example.hellostream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.example.hello.api.HelloService;
import com.example.hellostream.api.HelloStreamService;

/**
 * The module that binds the HelloStreamService so that it can be served.
 */
public class HelloStreamModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    // Bind the HelloStreamService service
    bindServices(serviceBinding(HelloStreamService.class, HelloStreamServiceImpl.class));
    // Bind the HelloService client
    bindClient(HelloService.class);
  }
}
