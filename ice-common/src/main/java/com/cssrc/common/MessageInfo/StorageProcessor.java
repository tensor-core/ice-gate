package com.cssrc.common.MessageInfo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StorageProcessor {

    String INPUT_STORAGE = "inputStorage";
    String OUTPUT_STORAGE = "outputStorage";

    @Input(INPUT_STORAGE)
    SubscribableChannel inputStorage();

    @Output(OUTPUT_STORAGE)
    MessageChannel outputStorage();
}