import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'
import light from "../styles/theme/light";

const sweetAlert = withReactContent(Swal);


const confirm = async (title: string, text: string,
                       onConfirm: VoidFunction,
                       onCancel: VoidFunction = () => {
                           // do nothing
                       }) => {
    const result = await sweetAlert.fire({
        title,
        text,
        icon: 'question',
        backdrop: true,
        background: light.background.color,
        confirmButtonColor: light.primary.background.color,
        confirmButtonText: 'OK',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
    });
    if (result.isConfirmed) {
        onConfirm()
    } else if (result.isDismissed) {
        onCancel()
    }
}

const errorWithTarget = async (title: string, html: HTMLElement) => {
    await sweetAlert.fire({
        title,
        html,
        icon: 'error',
        backdrop: true,
        background: light.primary.background.color,
        confirmButtonColor: light.secondary.background.color,
        confirmButtonText: 'OK'
    });
}

const error = async (title: string, text: string) => {
    await sweetAlert.fire({
        title,
        text,
        icon: 'error',
        backdrop: true,
        background: light.background.color,
        confirmButtonColor: light.primary.background.color,
        confirmButtonText: 'OK'
    });
}

export const alertBox = {
    error,
    errorWithTarget,
    confirm
}