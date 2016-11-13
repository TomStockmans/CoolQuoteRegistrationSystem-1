import {LogManager} from "aurelia-framework";
export var Logger = LogManager.getLogger('cqrs');

/*
 * Usage:
 * import {Logger} from "../util/cqrs-logging"
 * Logger.debug(`line added: ${line}`);
 */
