package com.cssrc.uc;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProductProcessor {

    String INPUT_PRODUCT_ADD = "inputProductAdd";
    String OUTPUT_PRODUCT_ADD = "outputProductAdd";

    @Input(INPUT_PRODUCT_ADD)
    SubscribableChannel inputProductAdd();

    @Output(OUTPUT_PRODUCT_ADD)
    MessageChannel outputProductAdd();

}
