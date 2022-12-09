(function() {
    AUI().applyConfig({
        groups: {
            santa_intranet_intragram: {
                base: MODULE_PATH + '/js/',
                combine: Liferay.AUI.getCombine(),
                filter: Liferay.AUI.getFilterConfig(),
                modules: {
                    'petcatalog' : {
                        path: 'presentation-module.js',
                        requires: ['liferay-portlet-base'],
                    }
                },
                root : MODULE_PATH + '/js/',
            },
        },
    });
})();