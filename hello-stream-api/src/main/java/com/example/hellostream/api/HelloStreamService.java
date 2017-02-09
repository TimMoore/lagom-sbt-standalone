/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.example.hellostream.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.namedCall;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

/**
 * The Hello stream interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the HelloStreamService service.
 */
public interface HelloStreamService extends Service {

  ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> stream();

  @Override
  default Descriptor descriptor() {
    return named("hello-stream")
        .withCalls(
            namedCall("stream", this::stream)
        ).withAutoAcl(true);
  }
}
