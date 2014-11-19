	var MaybankLoader = function (require, exports, module) {

		var exec = require("cordova/exec");

		/**
		 * Constructor.
		 *
		 * @returns {MaybankPayment}
		 */
		function MaybankPayment() {
		};

		/**
		 * Read code from scanner.
		 *
		 * @param {Function} successCallback This function will recieve a result object: {
		 *		text : '12345-mock',	// The code that was scanned.
		 *		format : 'FORMAT_NAME', // Code format.
		 *		cancelled : true/false, // Was canceled.
		 *	}
		 * @param {Function} errorCallback
		 */
		MaybankPayment.prototype.start = function (successCallback, errorCallback) {
			if (errorCallback == null) {
				errorCallback = function () {
				};
			}

			if (typeof errorCallback != "function") {
				console.log("MaybankPayment.start failure: failure parameter not a function");
				return;
			}

			if (typeof successCallback != "function") {
				console.log("MaybankPayment.start failure: success callback parameter must be a function");
				return;
			}

			exec(successCallback, errorCallback, 'MaybankPayment', 'start', []);
		};

		var maybankPayment = new MaybankPayment();
		module.exports = maybankPayment;

	};

	MaybankLoader(require, exports, module);

	cordova.define("cordova/plugin/MaybankPayment", MaybankLoader);
