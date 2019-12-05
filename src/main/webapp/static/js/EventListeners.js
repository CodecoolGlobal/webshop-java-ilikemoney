export const listeners = {

    customEventListener: function (element, event, handler) {

        const rootElement = document.querySelector('body');

        rootElement.addEventListener(event, function (e) {
                let target = e.target;
                while (target != null) {
                    if (target.matches(element)) {
                        handler(e);
                        return;
                    }
                    target = target.parentElement
                }

            },
            true)
    },

    addSortBtnListener: function (callback) {
        listeners.customEventListener("#filterBtn", "click", (e)=>callback(e));
    }
};