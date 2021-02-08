package com.ghostCoder.invoicePro.controllers;

import com.ghostCoder.invoicePro.WindowManager;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class WelcomeCtrl {
    WindowManager windowManager = new WindowManager();

    public void onClickStart(MouseEvent mouseEvent) throws IOException {
        windowManager.setScene("user-details-form", false);
    }
}
