/**
 * stringifies an object with native and/or BSON values back into
 * strict extended JSON.
 * @param  {Object} value               Object or value to stringify
 * @param  {Function|Array} replacer    Custom replacement
 * @param  {Number|String} space        Custom spacing
 * @return {String}                     JSON representation of value
 */
export function stringify(object:Object, replacer?:Function|Array<any>, space?:number|string):string;
