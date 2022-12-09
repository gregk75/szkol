module.exports = {
    build: {
        bundler: {
            config: {
                imports: {
                    'frontend-js-web': {
                        '/': '*',
                    },
                }
            },
            ignore: ['**/config.js', '**/*-module.js'],
        }
    },
};
