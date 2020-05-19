package com.brunonlemanski.pjatk.logger.logger;

import com.brunonlemanski.pjatk.logger.model.Bolid;
import com.brunonlemanski.pjatk.logger.model.Race;

public interface LoggerInterface {

    String LOG_PATH = "logs/";

    void log(Bolid bolid);

    void log(Race race);

}