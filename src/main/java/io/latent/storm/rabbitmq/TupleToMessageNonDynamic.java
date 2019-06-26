package io.latent.storm.rabbitmq;

import java.util.Map;

import backtype.storm.tuple.Tuple;
import io.latent.storm.rabbitmq.config.ProducerConfig;

public abstract class TupleToMessageNonDynamic extends TupleToMessage
{
  private String exchangeName;
  private String routingKey;
  private String contentType;
  private String contentEncoding;
  private boolean persistent;

  @Override
  protected void prepare(@SuppressWarnings("rawtypes") Map stormConfig)
  {
    ProducerConfig producerConfig = ProducerConfig.getFromStormConfig(stormConfig);
    exchangeName = producerConfig.getExchangeName();
    routingKey = producerConfig.getRoutingKey();
    contentType = producerConfig.getContentType();
    contentEncoding = producerConfig.getContentEncoding();
    persistent = producerConfig.isPersistent();
  }

  @Override
  protected String determineExchangeName(Tuple input)
  {
    return exchangeName;
  }

  @Override
  protected String determineRoutingKey(Tuple input)
  {
    return routingKey;
  }

  @Override
  protected String specifyContentType(Tuple input)
  {
    return contentType;
  }

  @Override
  protected String specifyContentEncoding(Tuple input)
  {
    return contentEncoding;
  }

  @Override
  protected boolean specifyMessagePersistence(Tuple input)
  {
    return persistent;
  }
}
