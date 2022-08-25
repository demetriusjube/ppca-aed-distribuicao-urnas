import { HttpErrorResponse } from '@angular/common/http';
import * as _ from 'lodash';
import { from, Observable, throwError as observableThrowError } from 'rxjs';
import { finalize } from 'rxjs/operators';
import swal from 'sweetalert2';

export class ErrorUtils {

    static displayError(error: any, defaultMessage?: string) {
        this.displayErrorAsObservable(error, defaultMessage).subscribe({
            next: () => {
            },
            error: () => {
            }
        });
    }

    static isJson(value: HttpErrorResponse): boolean {
        return (
            value &&
            value.headers &&
            /\bapplication\/json\b/.test(value.headers.get('Content-Type') || '')
        );
    }

    static rethrowError(errorResponse: HttpErrorResponse) {
        if (ErrorUtils.isJson(errorResponse)) {
            return observableThrowError(() => errorResponse.error);
        } else {
            return observableThrowError(() => {
                const erro =
                {
                    message:
                        errorResponse.status === 404
                            ? 'Recurso não encontrado'
                            : errorResponse.status === 0
                                ? 'Sessão expirada'
                                : 'Erro ' + errorResponse.status,
                    status: errorResponse.status,
                }
                return erro;
            });
        }
    }

    static displayErrorAsObservable(
        error: HttpErrorResponse,
        defaultMessage?: string
    ): Observable<any> {
        const err =
            ErrorUtils.isJson(error)
                ? error.error
                : error;

        let title =
            !err.message || err.message === 'No message available'
                ? defaultMessage || 'Erro'
                : err.message;

        let text = err.text && _.isString(err.text) ? `<p>${err.text}</p>` : `<p>${err}</p>`;

        return from(
            swal.fire({
                title: title,
                html: text,
                icon: 'error',
                confirmButtonText: 'OK',
            })
        ).pipe(
            finalize(() => {
                if (err.status === 0) {
                    window.location.href = window.location.href;
                }
            })
        );
    }
}
